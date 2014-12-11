//
//  ViewController.m
//  TweetProject
//
//  Created by iem on 11/12/2014.
//  Copyright (c) 2014 iem. All rights reserved.
//

#import "ViewController.h"

@interface ViewController (){
    NSArray *arrayEmojis;
    NSArray *arrayWords;
}
@property (weak, nonatomic) IBOutlet UIPickerView *picker;
@property (weak, nonatomic) IBOutlet UITextField *text;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    arrayEmojis = @[@";)", @":)", @":(", @":O", @"8)", @":o", @":D", @"mdr", @"lol"];
    arrayWords = @[@"dors", @"mange", @"suis en cours", @"galère", @"cours", @"poireaute"];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)backgroundTap:(id)sender {
    [self dismissKeyBoard];
}

-(void)dismissKeyBoard{
    [[self view] endEditing:YES];
}

// returns the number of 'columns' to display.
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView{
    return 2;
}


// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component{
    if(component == 0){
        return [arrayWords count];
    }
    else if(component == 1){
        return [arrayEmojis count];
    }
    return 0;
}

- (NSString *)pickerView:(UIPickerView *)pickerView
             titleForRow:(NSInteger)row
            forComponent:(NSInteger)component{
    if(component == 0){
        return [arrayWords objectAtIndex:row];
    }
    else if(component == 1){
        return [arrayEmojis objectAtIndex:row];
    }
    return @"";
}

- (IBAction)tweetIt:(id)sender {
    NSString *fullSentence;
    NSString *word = [arrayWords objectAtIndex:[[self picker] selectedRowInComponent:0]];
    NSString *emoji = [arrayEmojis objectAtIndex:[[self picker] selectedRowInComponent:1]];
    NSLog(@"%@ %@", word, emoji);
}

@end
