//
//  ViewController.m
//  TestDelegate
//
//  Created by iem on 11/12/2014.
//  Copyright (c) 2014 iem. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property(strong, nonatomic) UIColor *previousColor;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)tapAction:(id)sender {
    NSLog(@"Coucou !!");
//    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Wanna Change color ?"
//                                                        message:@"Sooooo Great !"
//                                                       delegate:nil cancelButtonTitle:@"Ok"
//                                              otherButtonTitles: nil];
//    [alertView show];
    
}

#pragma mark - Navigation

//In a storyboard-based application, you will often want to do a little preparation before navigation

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if([[segue identifier] isEqualToString:@"PickColor"]){
    ColorPicker *destinationController = [segue destinationViewController];
    [destinationController setDelegate:self];
    }
}

#pragma mark - Implement color picker

-(void)userDidChooseColor:(UIColor *)color{
    self.previousColor = self.view.backgroundColor;
    self.view.backgroundColor = color;
    [self dismissViewControllerAnimated:YES completion:nil];
    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Do you like it ?"
                                                        message:@"Do you want to keep it or go back to the previous one ?"
                                                       delegate:self
                                              cancelButtonTitle:@"I want to go back"
                                              otherButtonTitles: @"I keep it", nil];
    [alertView show];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if(buttonIndex == alertView.cancelButtonIndex){
        self.view.backgroundColor = _previousColor;
    }
}

@end
