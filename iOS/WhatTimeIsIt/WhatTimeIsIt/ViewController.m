//
//  ViewController.m
//  WhatTimeIsIt
//
//  Created by iem on 04/12/2014.
//  Copyright (c) 2014 iem. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UILabel *conterView;
@property (weak, nonatomic) IBOutlet UILabel *mResponse;
@property (weak, nonatomic) IBOutlet UILabel *mLabel;
@property UInt16 counter;

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
- (IBAction)whatTimeISIt:(id)sender {
    NSDate *now = [[NSDate alloc]initWithTimeIntervalSinceNow:0.0];
    NSDateFormatter *formatter = [[NSDateFormatter alloc]init];
    [formatter setDateFormat:@"yyyy-MM-dd 'at' HH:mm:ss"];
    _counter++;
    self.conterView.text =  [NSString stringWithFormat:@"%d", _counter];
    self.mResponse.text = [formatter stringFromDate:now];
}

@end
